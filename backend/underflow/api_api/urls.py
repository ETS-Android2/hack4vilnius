from django.urls import path
from .views import HeapUserViews

urlpatterns = [
    path('ooo/', HeapUserViews.as_view())
]