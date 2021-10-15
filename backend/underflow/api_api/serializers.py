from rest_framework import serializers
from .models import HeapUser


class HeapUSerSerializer(serializers.ModelSerializer):
    user_email = serializers.CharField(max_length=255)
    user_password = serializers.CharField(max_length=255)
    user_ID = serializers.IntegerField()

    class Meta:
        model = HeapUser
        fields = ('__all__')