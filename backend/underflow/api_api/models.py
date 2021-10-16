from django.db import models


class HeapUser(models.Model):
    user_email = models.CharField(max_length=255)
    user_password = models.CharField(max_length=255)
    user_ID = models.IntegerField(primary_key=True, unique=True)
    user_points = models.IntegerField(default=0)


class PointsList(models.Model):
    points = models.IntegerField()
    name = models.CharField(max_length=64)
    descriptions = models.CharField(max_length=255)