# Flender [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.jd-alexander/flender-plugin/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.jd-alexander/flender-plugin/)
Annotation triggered connectivity checking for Android

Aren't you tired of doing this all over your code? 

```java
if(connectivityUtil.isConnected(context))
{
    //do something
}
else
{
   Toast.makeText(context, "Internet unavailable", LENGTH_SHORT).show();
}

```
With Flender connectivity checking couldn't be easier. Just annoate your method with `@InternetRequired` and Flender does the rest. A toast will be shown if there's no internet available and the method won't execute. 

```java
@InternetRequired
public void doSomething()
{
    Log.v("Flender", "this plugin rocks");
}

```

Download
--------

```groovy
buildscript {
  repositories {
    mavenCentral()
  }

  dependencies {
    classpath 'com.github.jd-alexander:flender-plugin:1.0'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'flender'
```

Usage
-----
For connectivity checking to work Flender needs access to the current context so set it like this :          `Flender.init(this);` preferably in your application class or base activity. Once that's done, annotate away!.


```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Flender.init(this);
        test();
    }

    @InternetRequired
    public void test()
    {
        Log.v("Flender","Only logs if internet is available");
    }
}
```


You can also check for WiFi and Mobile connectivity with these annotations
```java
@WiFiRequired

@MobileRequired
```

If for some reason you don't want toast to be shown eg. using Flender in a service class then simply pass silent as a parameter to the respective annotation 
```java
@WiFiRequired("Silent")

@InternetRequired("Silent")
```

If you want to trigger custom actions based on the annotation being used you can use one of the listeners associated each annotation.

```java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Flender.init(this);
        Flender.setInternetUnavailable(new InternetUnavailable() {
            @Override
            public void flenderEvent() {
                materialDialog.show();
            }
        });
        test();
    }

    @InternetRequired
    public void test()
    {
    }
```
Once these listeners are used toasts,won't be triggered.

Contribution
------------

Please fork repository and contribute using pull requests.

Any contributions, large or small, major features, bug fixes, additional language translations, unit/integration tests are welcomed and appreciated but will be thoroughly reviewed and discussed.


License
--------

    Copyright 2015 Joel Dean

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

```







