import SwiftUI
import shared

struct ContentView: View {

    @State private var telefone: String = ""
    
	var body: some View {
        
        var text = ""

        TextField(
            "User name (email address)",
            text: $telefone
        )
        .disableAutocorrection(true)
        
        Button("Greeting") {
            openWhatsapp()
        }
        
	}
}

func openWhatsapp(){
    let urlWhats = "whatsapp://send?phone=(mobile number with country code)"
    if let urlString = urlWhats.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed){
        if let whatsappURL = URL(string: urlString) {
            if UIApplication.shared.canOpenURL(whatsappURL){
                if #available(iOS 10.0, *) {
                    UIApplication.shared.open(whatsappURL, options: [:], completionHandler: nil)
                } else {
                    UIApplication.shared.openURL(whatsappURL)
                }
            }
            else {
                print("Install Whatsapp")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
