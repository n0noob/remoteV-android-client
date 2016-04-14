package com.example.anoop.tcp_client;

/**
 * Created by Anoop on 4/9/2016.
 */

class MediaFile {
    String file;

    public MediaFile(){
    }

    public MediaFile(String f){
        file = new String(f);
    }

    public String get_file(){
        return file;
    }

    public void extract_and_set(String raw){
        file = raw.substring(1);
    }
}