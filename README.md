App Usage : Showing daily Covid Confirmed, Recovered and Death
 cases figures using covid19 opensource API.

Three concepts of Kotlin used :

Architecture components (Use of View Model and LiveData) :

I have used ViewModel class to make API Call using Retrofit library and
process response received from web service.
ViewModel does not contain any reference to associated UI
Controller. So, it survives UI Controller changes.
Used LifeCycle Aware LiveData class to store results and
update UI based on response received
from API.


Coroutines (When making API call using retrofit to handle concurrency) :

I used Coroutine in ViewModel while making an API Call.
await() method in Coroutine scope allows to do background thread task
of web service without blocking UI thread which benefited here to
effectively manage concurrency of operations.


RecyclerView with DataBinding :

Used RecyclerView to display the list of records with dataBinding
to bind Model class data to layout. LiveData class notifies observers
whenever data is updated. "binding.setLifecycleOwner(this)" allows binding to observe any changes in list of
LiveData Class.

Additional Resources Used :
1) Open Source Covid19 API
2) Retrofit Library.