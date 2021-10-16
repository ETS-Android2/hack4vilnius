from django.http.response import HttpResponse
from .models import HeapUser
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from .serializers import HeapUSerSerializer, HeapUserSafeSerializer

# Create your views here.

class HeapUserViews(APIView):
    def post(self, request):
        serializer = HeapUSerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "errors": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)
    
    def get(self, request):
        return Response({"status":"Success"})
    
# class LoginView(APIview):
#     def post(self, request):

class GetUserInfo(APIView):
    def get(self, request, id=None):
        if id:
            item = HeapUser.objects.get(user_ID=id)
            serializer = HeapUserSafeSerializer(item)
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        
        return Response({"status": "error", "data": "missing id"}, status=status.HTTP_200_OK)

        
class GetUsersInfo(APIView):
    def get(self, request, id=None):
        items = HeapUser.objects.all()
        serializer = HeapUserSafeSerializer(items, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        
        