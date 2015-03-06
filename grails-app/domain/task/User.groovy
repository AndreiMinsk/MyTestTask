package task

class User {

    String login
    String password
    String role = "user"

    static constraints = {
        login(unique: true,blank: false,nullable: false)
        password(password:true,blank: false)
        role(inList: ["admin","user"])
    }

    static  transients = ["admin"]

    boolean isAdmin(){
        return role == "admin"
    }

    boolean isUser(){
        return role == "user"
    }

    def beforeInsert = {
        password = password.encodeAsSHA()
    }
}
