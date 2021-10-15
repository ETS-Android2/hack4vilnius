from django.http.response import HttpResponse
from .models import HeapUser
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from .serializers import HeapUSerSerializer

# Create your views here.

class HeapUserViews(APIView):
    def post(self, request):
        # serializer = HeapUSerSerializer(data=request.data)
        return "Success"
        # if serializer.is_valid():
        #     serializer.save()
        #     return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        # else:
        #     return Response({"status": "error", "errors": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)
    
    def get(self, request):
        return HttpResponse("Success")