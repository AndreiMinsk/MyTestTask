package task


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    def beforeInterceptor = [action:this.&auth, except:['login', 'logout', 'authenticate']]


    def auth() {
        if(!session.user) {
            redirect(controller:"user", action:"login")
            return false
        }
        if(!session.user.admin){
            flash.message = "Tsk tsk—admins only"
            redirect(controller:"client", action:"index")
            return false
        }
    }

    def scaffold = true

    def login = {}

    def logout = {
        flash.message = "Goodbye ${session.user.login}"
        session.user = null
        redirect(action:"login")
    }

    def authenticate = {
        def user =
                User.findByLoginAndPassword(params.login,
                        params.password.encodeAsSHA())
        if(user){
            session.user = user
            flash.message = "Hello ${user.login}!"
            if(user.admin){
                redirect(controller:"admin", action:"index")
            } else{
                redirect(controller:"client", action:"index")
            }

        }else{
            flash.message =
                    "Sorry, ${params.login}. Please try again."
            redirect(action:"login")
        }
    }
}
