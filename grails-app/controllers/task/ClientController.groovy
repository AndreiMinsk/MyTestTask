package task


import org.grails.plugins.csv.*

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClientController {

    def beforeInterceptor = [action:this.&auth]


    def auth() {
        if(!session.user) {
            redirect(controller:"user", action:"login")
            return false
        }
    }

    def geocoderService



    def scaffold = true



    def search = {
        flash.message = "Search results for: ${params.q}"
        if (!params.q?.trim()) {
            render(view: 'index', model: [:])
        } else {


            def


                    resultsMap = Client.search(params.q, params)

            render(view: 'index',
                    model: [
                            clientInstanceList : resultsMap.results,
                            clientInstanceTotal: Client.countHits(params.q)
                    ]
            )
        }
    }

    def save(Client clientInstance)  {
        try {

            if (clientInstance.latitude == 0 || clientInstance.longitude == 0) {
                geocoderService.geocode(clientInstance);
            }
            if (clientInstance == null) {
                notFound()
                return
            }

            if (clientInstance.hasErrors()) {
                respond clientInstance.errors, view: 'create'
                return
            }

            clientInstance.save flush: true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'client.label', default: 'Client'), clientInstance.id])
                    redirect clientInstance
                }
                '*' { respond clientInstance, [status: CREATED] }
            }
        }catch (Exception e){
            flash.message = "Cannot save client !!! zip do not correspond to street or vice versa!!!try one more time!!!"
            redirect(controller:"client", action:"index")
            return false

        }
    }




    def update(Client clientInstance) {

        try {
            geocoderService.geocode(clientInstance)

            if (clientInstance == null) {
                notFound()
                return
            }

            if (clientInstance.hasErrors()) {
                respond clientInstance.errors, view: 'edit'
                return
            }

            clientInstance.save flush: true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'Client.label', default: 'Client'), clientInstance.id])
                    redirect clientInstance
                }
                '*' { respond clientInstance, [status: OK] }
            }
        }catch (Exception e){
            flash.message = "Cannot edit client!!! zip do not correspond to street or vice versa!!!try one more time!!!"
            redirect(controller: "client", action: "index")
            return false

        }
    }

    /*def geoloc = {

        render geocoderService.geocode()
    }*/

    def csvImport()  {
        def count = 0
            try {

                //request.getFile("myFile").inputStream.splitEachLine(',')
                request.getFile("myFile").inputStream.toCsvReader(/*['skipLines':0]*/).eachLine { fields ->
                if(fields[0]!='name'){
                Client z = new Client(name: fields[0],
                        email: fields[1],
                        street: fields[2],
                        zip: fields[3],
                        latitude: '0',
                        longitude: '0')

                if (z.latitude == 0 || z.longitude == 0) {
                    geocoderService.geocode(z);
                }

                z.save()
                    count++
                 }
                }



        }catch (Exception e){



            flash.message = /*"${e.stackTrace}"*/"cannot do import!!!!!"
            redirect(controller:"client", action:"index")

        }

        render (text: "you have imported $count clients!!!", contentType: 'text/html')




        }
    def map(){
        def clients = Client.list()
        [clients:clients]

    }




}
