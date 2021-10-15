from django.db import models


class HeapUser(models.Model):
    user_email = models.CharField(max_length=255)
    user_password = models.CharField(max_length=255)
    user_ID = models.IntegerField(primary_key=True)