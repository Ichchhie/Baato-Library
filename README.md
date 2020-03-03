# Baato-Library

Fast and efficient library to perform search and reverse geocoding

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installing

 1.Open up your project's build.gradle file. Add the following code:
 
```
allprojects{
 repositories {
  maven { url 'https://jitpack.io' }
 }
}

```

2.Open up your application's build.gradle file. Add the following code:

```
dependencies {
  implementation 'com.github.Ichchhie:Baato-Library:0.9.0'
}
```

### Prerequisites

#### To run on devices Android 9 and above,

1.Add a Network Security Configuration file
 
```
<?xml version="1.0" encoding="utf-8"?>
<manifest ... >
    <application android:networkSecurityConfig="@xml/network_security_config"
                    ... >
        ...
    </application>
</manifest>

```
2.In the res/xml/network_security_config.xml file, you can add localhost to the permitted cleartext traffic domain by adding:

```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">178.128.59.143</domain>
    </domain-config>
</network-security-config>
```

### Implementation

 #### 1. Search 
 
```
 new BaatoSearchService(this)
                .setAccessToken("your-access-token")
                .setQuery("your-query")
                .withListener(new BaatoSearchService.BaatoSearchRequestListener() {
                    @Override
                    public void onSuccess(List<Place> places) {
                      // get the list of search results here
                    }

                    @Override
                    public void onFailed(Throwable error) {
                      // get the error messages here
                    }
                })
                .doSearch();

```
 #### 2. Reverse GeoCode
 
 ```
new BaatoReverseGeoCodeService(this)
                .setAccessToken("your-access-token")
                .setGeoCode(new Geocode(lat, long))
                .setRadius("your-radius")
                .withListener(new BaatoReverseGeoCodeService.BaatoReverseGeoCodeRequestListener() {
                    @Override
                    public void onSuccess(List<Place> places) {
                       // you can get the address from the first item of the list i.e. places.get(0)
                    }

                    @Override
                    public void onFailed(Throwable error) {
                       // get the error messages here
                    }
                })
                .doReverseGeoCode();

```

## Built With

* [Retrofit](https://github.com/square/retrofit) - Used to handle API requests
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
