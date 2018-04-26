/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.report;

import java.io.BufferedWriter;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author samnoonafaq
 */
public class ReformatTestFile {

    private Scanner scanner;

	String replacealltimestamp(String html) {

        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("[0-9]{13}")
                .matcher(html);

        while (m.find()) {
            allMatches.add(m.group());
        }
        for (String entrySet : allMatches) {
            System.out.println(entrySet);
           	for (int i = 1; i <= 10; i++) {
    		               
                html = html.replace("passedeven td {background-color: #0A0}", "passedeven td {background-color: #66FF00}");
                html = html.replace("failedeven td,.stripe .attn {background-color: #D00}", "failedeven td,.stripe .attn {background-color: #F97B79}");
                html = html.replace("failedodd td,.attn {background-color: #F33}", "failedodd td,.attn {background-color: #F97B79}");
        		
            }
        }
        return html;
    }

    void writeLargerTextFile(String aFileName, String html) {
        //  Path path = Paths.get(aFileName);
          try{
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aFileName), "ISO-8859-1"));
          writer.write(html);
          writer.close();
          }catch(Exception e){
          	System.out.println("Error-"+e);
         	}//end of catch
      }

      String readLargerTextFile(String aFileName){
          String html = "";
          Path path = Paths.get(aFileName);
         try{
      	   scanner = new Scanner(path, "ISO-8859-1");
             while (scanner.hasNextLine()) {
                  //process each line in some way
                  html = html + scanner.nextLine() + "\n";
              }
          }catch(Exception e){System.out.println("Error-"+e);}//end of catch
          return html;
      }
}
