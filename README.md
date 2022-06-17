# Scala Native :heart: Scala.js :heart: webview

![2022-06-17 11 32 36](https://user-images.githubusercontent.com/1052965/174283493-0af4f358-53a9-46bf-bfa5-734906489544.gif)


This is a very small demonstrator of 

1. Using [webview](https://github.com/webview/webview) library from Scala Native 
2. Bundling a Scala.js frontend as part of a SN binary 
3. Running Scala.js frontend inside of a desktop application
4. _soon_ invoking a function on the backend

### Note: this exact version only works on MacOS

Similar to how different the [webview setup instructions](https://github.com/webview/webview) are across platforms,
same needs to be done to the SN compilation/linking.

It's not by any means complicated, but for a demonstrator app it's too much effort to test it on all 3 platform.

## Running on MacOS 

If you have 

1. Build webview static library:

```
git submodule update --init
cd webview
clang++ webview.cc -std=c++17 -framework WebKit -c 
ar rc webview.a webview.o
```

2. Run the app:

```
sbt native/run
```

That's it.
