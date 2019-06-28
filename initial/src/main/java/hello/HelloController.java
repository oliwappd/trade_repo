package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import hello.TradeRepo;
import javax.servlet.http.HttpServletResponse;


@RestController
public class HelloController {

	public HelloController() {
		repo = new TradeRepo();
	}
    
    @RequestMapping("/")
    public String index(@RequestParam(required = false) String abc) {
        return "Greetings from Spring Boot!" + abc + "!!!";
    }
    

	@RequestMapping("/service/tradeDetails")
    public String serviceFunc(
    	@RequestParam(required = false) Integer tradeId, 
    	@RequestParam(required = false) String  tradeType,
    	HttpServletResponse response
    	) {
    		System.out.println("Got request (" + tradeId + "," + tradeType + ")");
            return getTradeDetails(tradeType, tradeId, response);
        }

	@RequestMapping("/xmltest")
    public String serviceFunc(
    	HttpServletResponse response
    	) {
    		response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return "Got XML!";
        }


   TradeRepo repo;

   String getTradeDetails(String tradeType, Integer tradeId, HttpServletResponse response) {
   		if ( tradeType == null || tradeId == null ) {
   			// Missing mandatory args
   			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
   			return "err";
   		}
 
   		TradeRepo.TradeDetails td = repo.getTradeDetails(tradeId, tradeType, response);
   		System.out.println("Retrieved data: "+td.data+ " updates => "+ td.tradeUpdates);
   		return td.data;
   }

}
