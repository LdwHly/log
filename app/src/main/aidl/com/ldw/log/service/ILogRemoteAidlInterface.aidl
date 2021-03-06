// ILogRemoteAidlInterface.aidl
package com.ldw.log.service;

// Declare any non-default types here with import statements

interface ILogRemoteAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
        void println(CharSequence tag,CharSequence url,CharSequence parms,CharSequence response);
        void d(CharSequence tag,CharSequence msg);
        void printlnTag(CharSequence packageName,CharSequence tag,CharSequence childTag,CharSequence msg);
        void showWindow();
        void hideWindow();
}
