package hello;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class TradeRepo {

	HttpServletResponse response_;
	Random rand;
	int max, min;

	TradeRepo(){
		rand = new Random();
		max = 500;
		min = 1;
	}

	public class TradeDetails {
		
		public String data;
		public int    tradeUpdates;

		TradeDetails() {
			data = "{}";
			tradeUpdates = 100;
		}
	}

	public TradeDetails getTradeDetails(int tradeId, String tradeType, HttpServletResponse response)
	{
		response_ = response;
		TradeDetails ret = new TradeDetails();
		switch(tradeType){
			case "FX": getFXTradeDetails(tradeId, ret); break;
			case "FI": getFITradeDetails(tradeId, ret); break;
			case "EQ": getEQTradeDetails(tradeId, ret); break;
			default: break; 
		}
		return ret;
	}

	private TradeDetails getFXTradeDetails(int tradeId, TradeDetails ret)
	{
		response_.setStatus(HttpServletResponse.SC_ACCEPTED);
		ret.data = "{\"TradeType\": \"FX\", \"SYM\":\"GBP/USD\", \"Rate\":1.2426, \"Amount\":1000000}";
		ret.tradeUpdates = rand.nextInt(((max - min) + 1) + min);
		return ret;
	}


	private TradeDetails getFITradeDetails(int tradeId, TradeDetails ret)
	{
		response_.setStatus(HttpServletResponse.SC_ACCEPTED);
		ret.data = "{\"TradeType\": \"FI\", \"SYM\":\"UKGLT_12Mnth\", \"Rate\":0.297, \"Amount\":2000000}";
		ret.tradeUpdates = rand.nextInt(((max - min) + 1) + min);
		return ret;
	}

	private TradeDetails getEQTradeDetails(int tradeId, TradeDetails ret)
	{
		int tradeCt;
		if (tradeId < 10000) {
			// retrieve trade details from internal database
			for (int nn=0; nn<10000000; nn++) {tradeCt=nn;}
			ret.tradeUpdates = tradeId;

			try{Thread.sleep(3000);} catch(java.lang.InterruptedException ex) {}
			response_.setStatus(HttpServletResponse.SC_ACCEPTED);
			ret.data = "{\"TradeType\": \"EQ\", \"SYM\":\"APPD\", \"Rate\":53.08, \"Amount\":100000}";
			return ret;
		}
		response_.setStatus(HttpServletResponse.SC_ACCEPTED);
		ret.data = "{\"TradeType\": \"EQ\", \"SYM\":\"CSCO\", \"Rate\":53.08, \"Amount\":100000}";
		return ret;
	}
}


