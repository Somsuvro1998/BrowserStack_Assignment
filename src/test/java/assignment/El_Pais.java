package assignment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.darkprograms.speech.translator.GoogleTranslate;
import io.github.bonigarcia.wdm.WebDriverManager;

public class El_Pais{
	public WebDriver driver;

	@BeforeClass
	@Parameters({"Browser"})
	public void beforeClass(String Browser)
	{
		if(Browser.equalsIgnoreCase("Chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void el_pais()
	{

		driver.get("https://elpais.com/");
	
		driver.findElement(By.xpath("//span[text()='Accept']")).click();
		String lang = driver.findElement(By.xpath("(//span[contains(text(),'US English')])[1]")).getText();
		
		//Ensuring that the website's text is displayed in Spanish.
		if(lang.equalsIgnoreCase("US English"))
		{
			System.out.println("The Website is in English");
		}
		else
		{
			System.out.println("The Website is in Spanish");
		}
		//Navigate to the Opinion section of the website.
        driver.findElement(By.xpath("(//a[text()='Opinión'])[1]")).click();
        
        //Fetch the first five articles in this section.
         WebElement article1 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--c c--m-n   '])[1]"));
         String header1 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--c c--m-n   '])[1]/header/h2/a")).getText();
         String body1 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--c c--m-n   '])[1]/p")).getText();
         
         WebElement article2 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--c c--m-n   '])[2]"));
         String header2 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--c c--m-n   '])[2]/header/h2/a")).getText();
         String body2 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--c c--m-n   '])[2]/p")).getText();
         
         WebElement article3 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--m-n   '])[1]"));
         String header3 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--m-n   '])[1]/header/h2/a")).getText();
         String body3 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--m-n   '])[1]/p")).getText();
         
         WebElement article4 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--m   '])[1]"));
         String header4 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--m   '])[1]/header/h2/a")).getText();
         String body4 = driver.findElement(By.xpath("(//article[@class='c c-o c-d c--m   '])[1]/p")).getText();
         
         WebElement article5 = driver.findElement(By.xpath("(//article[@class='c c-d c--m   '])[1]/header/h2/a"));
         String header5 = driver.findElement(By.xpath("(//article[@class='c c-d c--m   '])[1]/header/h2/a")).getText();
         String body5 = driver.findElement(By.xpath("(//article[@class='c c-d c--m   '])[1]/p")).getText();
		
         //Print the title and content of each article in Spanish.
       
         System.out.println("Title 1: "+header1+" | Content1: "+body1);
		 System.out.println("Title 2: "+header2+" | Content2: "+body2);
		 System.out.println("Title 3: "+header3+" | Content3: "+body3);
		 System.out.println("Title 4: "+header4+" | Content4: "+body4);
		 System.out.println("Title 5: "+header5+" | Content5: "+body5);
		 System.out.println("------------------------------------------------------------------");
		 
		 //Use a translation API of your choice
		 //Translate the title of each article to English
		 //Print the translated headers.
		 String allHeaders=null;
		 try {
			 String header1InEnglish=GoogleTranslate.translate("en","header1 : "+header1);
			 System.out.println(header1InEnglish);
			 
			 String header2InEnglish=GoogleTranslate.translate("en","header2 : "+header2);
			 System.out.println(header2InEnglish);
			 
			 String header3InEnglish=GoogleTranslate.translate("en","header3 : "+header3);
			 System.out.println(header3InEnglish);
			 
			 String header4InEnglish=GoogleTranslate.translate("en","header4 : "+header4);
             System.out.println(header4InEnglish);
			 
     		 String header5InEnglish=GoogleTranslate.translate("en","header5 : "+header5);
     		 System.out.println(header5InEnglish);
			 
			 allHeaders=header1InEnglish+" "+header2InEnglish+" "+header3InEnglish+" "+header4InEnglish+" "+header5InEnglish;
		 }
	 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 System.out.println("----------------------------------------------------------------------");
		 
		 //From the translated headers, identify any words that are repeated more than twice across all headers combined
		 //Print each repeated word along with the count of its occurrences
		 allHeaders = allHeaders.toLowerCase();

		 String[] words = allHeaders.split("\\s+");

         Map<String, Integer> wordCounts = new HashMap<String, Integer>();

         for (String word : words) {
	            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
	        }

         System.out.println("Words repeated more than twice across all headers:");
	        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
	            if (entry.getValue() > 2) {
	                System.out.println(entry.getKey() + ": " + entry.getValue());
	            }

	}
	        
	}
	@AfterClass
	public void afterClass()
	{
		driver.quit();
	}
}
