# google.issue.161245048

https://issuetracker.google.com/issues/161245048

* Open the app
* Click the button which launches the second activity
* The second activity launches the SAF file picker
* Select any path in the SAF file picker
* The second Activity will return results to the first activity
* The first activity will wait a few seconds until the second activity is destroyed
* After a few seconds the app will crash when we try to persist the uri permission