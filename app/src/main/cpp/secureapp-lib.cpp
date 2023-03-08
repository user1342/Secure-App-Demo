#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("secureapp");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("secureapp")
//      }
//    }
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_secureapp_SettingsActivity_00024SettingsFragment_calculateSignatureHash(
        JNIEnv *env, jobject thiz, jstring signature) {
    char buff[128];
    const char *str = (*env).GetStringUTFChars(signature,0);

    unsigned long hash = 5381;
    int c;

    while (c = *str++)
        hash = ((hash << 5) + hash) + c; /* hash * 33 + c */

    return hash;
}