package task

import grails.transaction.Transactional

@Transactional
class GeocoderService {

    def base = 'http://maps.googleapis.com/maps/api/geocode/xml?'
    def slurper = new XmlSlurper();
    def geocode(Client client) {
        /*String street = "Oyster Row";
        String postcode = 220025*/
        def qs = []
        qs << "address=" + URLEncoder.encode(client.street)
        qs << "components=postal_code:"+URLEncoder.encode(client.zip)
        qs << "sensor=false"




        def myUrl = base+qs.join('&')


        def response = slurper.parse(myUrl)

        client.latitude = response.result.geometry.location.lat.toDouble()
        client.longitude = response.result.geometry.location.lng.toDouble()

        /*  def map = [1:response.result.geometry.location.lat.toDouble(),2:response.result.geometry.location.lng.toDouble()]
          return  map*/





    }
}
