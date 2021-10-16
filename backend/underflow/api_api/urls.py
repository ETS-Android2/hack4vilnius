from django.urls import path
from .views import HeapUserViews, GetUserInfo, GetUsersInfo

urlpatterns = [
    path('ooo', HeapUserViews.as_view()),
    path('user/<int:id>', GetUserInfo.as_view()),
    path('allusers/', GetUsersInfo.as_view()),

]