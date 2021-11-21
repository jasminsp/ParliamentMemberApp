# ParliamentMemberApp
Application is part of a individual project in Metropolia University of Applied Sciences, where I was supposed to create an application which shows details
of different parliament parties and parliament members in Finland. 

User can browse through the application starting by choosing the party. They will then see members of that specific party, 
which after they can choose specific member. Once they choose a member, they will see detailed information of that member, like the 
member, give a comment and view comments. Once they are done, they can use the bottomnavigation bar to return to the party listing.

MainActivity
- Navigation component
- Base for the fragments

Fragments:
List of parties
- Gridview of partylogos shown with recyclerview

List Of Members
- Listview of partylogos shown with recyclerview
- Show if member is liked or not

Detailed page for members
- Send comments about member
- View comments about member
- Like/dislike member with checkbox heartButton
- Fetch image with glide from internet

List of Comments
- Show list of comments with date, time and actual comment

Other features used in the app: 
- Room database/dao for comments, votes and parliament member data
- LiveData to observe live updates
- VMMV framework with repository and API service for fetching member information from API: Retrofit and Moshi.
- ViewModel for all fragments


# **Fragments in the application**
List of different parties in Finland<br/> 
<img width="415" alt="ParliamentApp_view1" src="https://user-images.githubusercontent.com/74709722/142756689-b7e8cc8b-826e-4c3c-b3ae-18b169b6c9d8.png"><br/> 
List of the party members<br/> 
<img width="420" alt="ParliamentApp_view2" src="https://user-images.githubusercontent.com/74709722/142756698-6bd52a00-2283-45ee-b820-50ebbeb2099c.png"><br/> 
Party member view<br/> 
<img width="422" alt="ParliamentApp_view3" src="https://user-images.githubusercontent.com/74709722/142756702-c6cf4ecb-45fe-4ed9-bff1-988e425a1e60.png"><br/> 
Comments of a specific party member<br/> 
<img width="423" alt="ParliamentApp_view4" src="https://user-images.githubusercontent.com/74709722/142756706-50d23d17-5dd2-44ad-ae02-be3e13bab7f3.png"><br/> 
