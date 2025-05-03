package com.demo.mi_cafeteria.utils;

 public class NullOrWhiteSpaceUtils {

     public static Boolean isNull(String var){
         return var==null?Boolean.TRUE:Boolean.FALSE;
     }

     public static Boolean isBlankOrWhiteSpace(String var){
         return var.isBlank();
     }

}
