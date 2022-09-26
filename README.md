ROY WATSON

***FoodData Project***

**Description:**

I wanted to create a sample/demo app to use to illustrate my skills and competencies in modern Android development technologies. My hope is that it can also prove to be educational and helpful for others. It specifically demonstrates:

- Rest API calls.

- MVVM pattern.

- Repository pattern

- Kotlin language

- Kotlin Extension functions.

- Kotlin Coroutines and lifecycle aware components.

- Kotlin Flows for reactive streams.

- Jetpack Navigation with Compose BottomNavigation nav bar.

- Jetpack Compose.

- Jetpack Compose Material Components v.3

- Jetpack Compose Paging lists

- Hilt (Dagger 2) Dependency injection.

- Room ORM local Sqlite data persistence.

- Jetpack ProtoDataStore (ProtoBuffer replacement for SharedPreferences)

- Aspects of Clean architecture principles.

- Google ML (Machine Learning) Kit Barcode scanning (planned future addition).

- Google ML (Machine Learning) Kit Text Recognition (planned future addition).

I chose to use the USDA’s *FoodData Central* online database (<https://fdc.nal.usda.gov/>). I chose this database for a variety of reasons:

- It’s free without geographic limitation.

- An API key is immediately available with on-line registration.

- It’s large with millions of records.

- Recently, the USDA has been adding “Branded” foods; those from commercial sources with public brand names such as

**Implementation Strategy:**

I am starting with functionality before style. I am getting all of the plumbing in place and will develop the UI around that.

**Build and Run the app:**

You will need to obtain a free API key. You can acquire one at <https://fdc.nal.usda.gov/api-key-signup.html>

Using that key, create a file in the project root called “apikey.properties” that contains the following line:

FOODDATA_KEY="\<your_api_key\>"

Import the code into a new Android Project.

**Status:**

The current repo has a rudimentary UI, enough to exercise the search functionality and display the resultant data set.

The Search page (com.delasystems.fooddata.presentation.search. FoodSearchScreen.kt) has three TextFields to receive search criteria. These fields can be used singularly for simple searched or in combination to achieve a more complex search:

- “Contains ANY of these words:” will return results that include ANY of the words antered here. This is essentially a logical “or” between all entered terms. Clearly adding search terms here expands the possible returned records.

- “Contains ALL of these words:" will only return records that contain ALL of the words entered here. This is essentially a logical “and” between all entered terms. Clearly adding search terms here contracts the possible returned records.

- “"Contains NONE of these words:" will only return results that “DO NOT” include any of the words entered here. Clearly adding search terms here reduces the number of returned values. This field is most useful when used in combination with the previous two fields. Given the size of the underlying database, using this field alone will usually result in uselessly large returned datasets; but it does work and does return huge datasets that are useful for demonstrating the Jetpack Paging library by yielding smooth scrolling with gigantic datasets.

Your most recent search terms are persisted using Jetpack’s ProtoDataStore as a modern, recommended replacement for Android’s traditional SharedPreferences. All searches are saved into the search history table (“tb;\_search_history”)

The results of the search are displayed in a currently rudimentary list of Material Component Cards.

Clicking on any card will navigate to a currently immature screen that displays the nutritional details of the selected food.

**Near-term Plan:**

To-do (in no particular order):

- Add a mechanism to the main search screen to display and allow the selection and re-use of a historical search (those searches saved “tb;\_search_history”).

- Save selected foods (foods selected from a search list) into the food history table (“tbl_food_history”)

- Implement the “History” screen (navigated to using the “History” bottom navigation item).

- Add a mechanism to designate a displayed food as a favorite. Probably a heart icon in the heading when displaying a food details screen. Clicking that will designate this food as a favorite in the “tbl_food_history” table by setting the “is_favorite” flag.

- Implement “Favorites” screen to display the historical foods filtered by the “is_favorite” flag. This implementation is relatively trivial as it is really just a duplication of the History screen with an additional filter.

- Polish the UI.

**Long-term Future:**

To-do (in no particular order):

- Google ML (Machine Learning) Kit Barcode scanning (planned future addition).

- Google ML (Machine Learning) Kit Text Recognition (planned future addition). Read nutrition panel and product label.

- Allow user to create personal database based on upc from barcode and nutrition panel data scanned above.

- Allow user to create shopping lists based on all data, on-line and personal.

- Allow “check-off” of picked items.

**Xtra-Long-term Future:**

To-do (in no particular order):

- Log position of user to identify current grocery store

- Allow user to log location of item within a grocery store.

- Sort shopping list in “picking” order.

- Allow price entry when picking items (possible price scan? Text? Or Barcode?).

**License:**

Copyright (C) 2022 Roy Watson​

Permission is hereby granted, free of charge, to any person obtaining a copy of thissoftware and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.​THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULARPURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLEFOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USEOR OTHER DEALINGS IN THE SOFTWARE.
