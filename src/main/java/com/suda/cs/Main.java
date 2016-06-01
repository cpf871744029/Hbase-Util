package com.suda.cs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei on 2016/5/31.
 */
public class Main {
    public static void main(String[] args){
        if(args.length !=1){
            System.out.println("xml file is not one, you should provide only one xml file");
            return;
        }
        String fileName = args[0];
        List<Table> tables = new ArrayList<Table>();
        ParseXml.parserXml(fileName,tables);
        for(Table table: tables){
            HbaseOperate.createTable(table);
        }
    }
}
