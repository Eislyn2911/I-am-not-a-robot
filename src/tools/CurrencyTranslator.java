package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.JSONObject;

public class CurrencyTranslator {
	DecimalFormat decimalformat = new DecimalFormat("0.00");
	
	private String baseCurrency;
	private String targetCurrency;
	private double amountToConvert;
	private String code;
	private String updated;
	private double conversionRate;
	
	public CurrencyTranslator(String baseCurrency, String targetCurrency, double amountToConvert) {
		this.baseCurrency = baseCurrency.toUpperCase();
		this.targetCurrency = targetCurrency.toUpperCase();
		this.amountToConvert = amountToConvert;
	}
	
	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public double getAmountToConvert() {
		return amountToConvert;
	}

	public void setAmountToConvert(double amountToConvert) {
		this.amountToConvert = amountToConvert;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public double getAmount() {
		return amountToConvert;
	}

	public void setAmount(double amount) {
		this.amountToConvert = amount;
	}

	public String getResponse(String apiKey) {
		try {
			String url = "https://api.currencyapi.com/v3/latest?apikey=" + apiKey + "&currencies=" + targetCurrency + "&base_currency=" + baseCurrency;

		    URL urlForGetRequest = new URL(url);
		    String readLine = null;
		    HttpURLConnection conection = (HttpURLConnection)urlForGetRequest.openConnection();
		    conection.setRequestMethod("GET");
		    int responseCode = conection.getResponseCode();

		    if (responseCode == HttpURLConnection.HTTP_OK) {
		        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
		        StringBuffer response = new StringBuffer();
		        while ((readLine = in.readLine()) != null) {
		            response.append(readLine);
		        }
		        in.close();
		        return response.toString();
		        
		    } else {
		        throw new Exception("Error in API Call");
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		} 
		return null;
	}
	
	public void extractData(String responseStr) {
		//Last updated
		JSONObject conversionObject = new JSONObject(responseStr);
		JSONObject metaObject = conversionObject.getJSONObject("meta");
		updated = metaObject.getString("last_updated_at");	
		
		//code
		JSONObject dataObject = conversionObject.getJSONObject("data");
		JSONObject currencyObject = dataObject.getJSONObject(targetCurrency);
		code = currencyObject.getString("code");
		conversionRate = currencyObject.getDouble("value");
	}
	
	public double calculateConversion() {
		double convertedAmount = amountToConvert * conversionRate;
		
		convertedAmount = Double.parseDouble(decimalformat.format(convertedAmount).toString());
		return convertedAmount;
	}
}
