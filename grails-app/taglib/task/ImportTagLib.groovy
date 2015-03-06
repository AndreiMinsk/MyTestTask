package task

class ImportTagLib {
    static defaultEncodeAs = [taglib: 'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def importControl = {
        if (request.getSession(false) && session.user) {

            out << """[${
                link(action: "upload",
                        controller: "client") { "CSV Import Clients" }
            }]"""

        }

    }
}
