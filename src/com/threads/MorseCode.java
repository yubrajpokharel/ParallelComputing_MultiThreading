package com.threads;

/**
 * Created by yubraj on 1/8/17.
 */



import java.io.*;
class MorseCode{
    public static void main(String[]b){
        String s,
        t="-",
        m=t+t,
        o=m+t,
        z="",
        e=".",
        i=e+e,
        p=t+e,
        a=e+t,
        n=i+e,
        c[]={o+m,a+o,i+o,n+m,n+a,n+i,p+n,m+n,o+i,o+p,z,z,z,z,z,z,z,a,t+n,p+p,t+i,e,i+p,m+e,n+e,i,e+o,p+t,a+i,m,p,o,a+p,m+a,e+p,n,t,i+t,n+t,e+m,p+a,p+m,m+i};
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try{
            s=r.readLine().toUpperCase();
            for(int j=48;j<91;j++)
                s=s.replace(z+(char)j,c[j-48]+" ");
            System.out.println(s);
        }catch(Exception x){}}}
