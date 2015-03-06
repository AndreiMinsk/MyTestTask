import task.User

class BootStrap {

    def init = { servletContext ->

        new User(
                login: "user",
                password: "user",
                role: "user"
        ).save()

        new User(
                login: "admin",
                password: "admin",
                role: "admin"
        ).save()
    }
    def destroy = {
    }
}
