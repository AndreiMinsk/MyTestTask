package task

class Client {

    String name
    String email
    String street
    String zip
    double latitude
    double longitude

    static constraints = {
        name(blank: false)
        email(email: true,unique: true,blank: false)
        street()
        zip()
    }

    static searchable = true






    def  map = {

    }
}
