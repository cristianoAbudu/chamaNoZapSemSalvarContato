import SwiftUI
import shared

struct ContentView: View {

    
    @State private var telefone: String = ""
    
	var body: some View {
        
        TextField(
            "Telefone com DDD",
            text: $telefone
        )
        .disableAutocorrection(true)
        .keyboardType(.numberPad)
        .multilineTextAlignment(.center)
        
        Button("Chamar no Whats") {
            openWhatsapp(telefone: telefone)
        }
        
	}
}

func openWhatsapp(telefone: String){
    let urlWhats = "whatsapp://send?phone="+telefone
    if let urlString = urlWhats.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed){
        if var whatsappURL = URL(string: urlString) {
            if UIApplication.shared.canOpenURL(whatsappURL){
                if #available(iOS 10.0, *) {
                    UIApplication.shared.open(whatsappURL, options: [:], completionHandler: nil)
                } else {
                    UIApplication.shared.openURL(whatsappURL)
                }
            }
            else {
                
                whatsappURL = URL(string: "http://wa.me/"+telefone)!
                
                if #available(iOS 10.0, *) {
                    UIApplication.shared.open(whatsappURL, options: [:], completionHandler: nil)
                } else {
                    UIApplication.shared.openURL(whatsappURL)
                }
                
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
