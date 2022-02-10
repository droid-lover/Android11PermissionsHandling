# Android11PermissionsHandling


This codebase is related to one weird flow in Android 11 permissions handling. 

In android 11 this is the weird flow-  permissions popups are dismissible, 
and when we dismiss we get callback like user selected never ask again :grin:

<img src="https://media.giphy.com/media/TXFbYzcwXS0AzBICbG/giphy.gif">


I also wrote a blog on it with solution, where you check more about this problem and its workaround solution.



<a href="https://medium.com/native-mobile-bits/handling-permissions-in-android-11-fa79602a4724">Check blog here with Solution :) </a> 


also While requesting permissions there is slight difference please note that as well, than only you will get callback in ```onRequestPermissionsResult``` method.


in activity - 

```ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)```


and in fragment -

```requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_STORAGE_PERM)```

If we follow this way of requesting permissions based on Activity/Fragment it will surely give us the callback. #verified.
Lets connect over here :) 
  
[![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-0e76a8?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/sachin-rajput-998b48105/)
[![Website Badge](https://img.shields.io/badge/Medium-3b5998?style=flat-square&logo=google-chrome&logoColor=white)](https://droid-lover.medium.com/)
[![Stackoverflow Badge](https://img.shields.io/badge/-Stackoverflow-FFA500?style=flat-square&logo=Stackoverflow&logoColor=orange)](https://stackoverflow.com/users/7193506/sachin)
[![Youtube Badge](https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://www.youtube.com/channel/UCTjQSpx2waqXTC37AgM8qyA)
