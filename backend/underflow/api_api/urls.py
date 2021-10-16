from django.urls import path
from .views import HeapUserViews, GetUserInfo

urlpatterns = [
    path('ooo', HeapUserViews.as_view()),
    path('user/<int:id>', GetUserInfo.as_view()),
]