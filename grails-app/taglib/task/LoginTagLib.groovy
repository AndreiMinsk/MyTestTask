package task

class LoginTagLib {
    static defaultEncodeAs = [taglib:'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def loginControl = {
        if(request.getSession(false) && session.user){
            out << "Hello ${session.user.login} "
            out << """[${link(action:"logout",
                    controller:"user"){"Logout"}}]"""
        }

    }
}
