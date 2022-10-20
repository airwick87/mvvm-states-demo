
![phone4](https://user-images.githubusercontent.com/31004679/197071518-5dba5557-0273-486f-84e6-1a5962a92e03.png)
![phone5](https://user-images.githubusercontent.com/31004679/197071561-ca5bc1dd-9615-48c6-b01d-287c4f081467.png)
![tab1](https://user-images.githubusercontent.com/31004679/197071619-4f2d4676-a151-4ee7-8c29-4651dcd0834a.png)

# Approach ->
* Clean Architecture with Multi Module Architecture i.e. UI (app), Data, Domain
    * Used widely by the android community, encourages SOLID and consistency for developers
* MVVM + States
    * Established pattern that encourages testable and maintainable code
* Hilt/Retrofit 
    * dependency injection
    * Qualifier usage for Auth services
* Jetpack Nav - to navigate around fragments
* Coroutines with Flow - async. to gather and manipulate data as required 
* Repositories with Usecases - to usher data through the onion architecture as well as allowing reuseable scenarios
* Grid will change based on screen size using smallest width qualifiers
* Accessibility -> Used standard components so Talkback is working as expected (tested on Google 4A), however we could expand on this by using content descriptions and custom actions where required
* Unit tests are based on mockito


Technical Assumptions ->
* No pagination as required for current Requirements, if needed, a callback can be added to recyclerView and we request the next page / update the adapter
* I’ve used the max value price for the sake of saving time
* Currency symbol has been hardcoded, getting currency by locale is not viable as the user could have various locale combinations
* Ui is a little unpolisheded due to Time constraints
* No database required, image caching handled by Glide


