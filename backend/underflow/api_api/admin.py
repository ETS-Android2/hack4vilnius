from django.contrib import admin
from .models import HeapOrganisation, HeapUser, Locations, PointsList

admin.site.register(HeapUser)
admin.site.register(Locations)
admin.site.register(PointsList)
admin.site.register(HeapOrganisation)