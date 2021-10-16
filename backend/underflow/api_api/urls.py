from django.urls import path
from .views import HeapUserViews, GetUserInfo, GetUsersInfo, LocationsView, LoginView, PointsAdditionsView

urlpatterns = [
    path('ooo', HeapUserViews.as_view()),
    path('registration', HeapUserViews.as_view()),
    path('login', LoginView.as_view()),
    path('user/<int:id>', GetUserInfo.as_view()),
    path('allusers', GetUsersInfo.as_view()),
    path('locations', LocationsView.as_view()),
    path('points', PointsAdditionsView.as_view()),
    path('points/<int:receiver_id>', PointsAdditionsView.as_view())

]