package task

class AdminController {

    def beforeInterceptor = [action:this.&auth]

    def auth() {
        if(!session.user) {
            redirect(controller:"user", action:"login")
            return false
        }else if(!session.user.admin){
            flash.message = "Tsk tsk—admins only"
            redirect(controller:"client", action:"index")
            return false
        }
    }

    def index() {}
}
