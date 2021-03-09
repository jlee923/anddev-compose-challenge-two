# Seconds CountDown Timer

<!--- Replace <OWNER> with your Github Username and <REPOSITORY> with the name of your repository. -->
<!--- You can find both of these in the url bar when you open your repository in github. -->
![Workflow result](https://github.com/<OWNER>/<REPOSITORY>/workflows/Check/badge.svg)


## :scroll: Description
This simple app allows a user to enter the number of seconds to count down. One can then start and
stop the timer at any point.  Starting the timer again will restart the clock at the original time.


## :bulb: Motivation and Context
Integrating with the ViewModel was the most interesting piece of this. I initially looked at using
LiveData, but realized that I only needed to return `mutableStateOf()` from the ViewModel.  It was
also a revelation when I realized that I should pass the data and events through lambdas instead of
passing the entire ViewModel to all the Composable Views.

## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```