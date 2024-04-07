package com.udacity.project4.locationreminders.reminderslist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.project4.base.BaseViewModel
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import com.udacity.project4.locationreminders.data.local.RemindersLocalRepository
import kotlinx.coroutines.launch

class RemindersListViewModel(
    app: Application,

    private val dataSource: ReminderDataSource
    //BaseViewModel extends AndroidViewModel for access to the Application context. This ViewModel consists solely
    //of several class properties that are of type SingleLiveEvent, which are MutableLiveData objects.
) : BaseViewModel(app) {
    // list that holds the reminder data to be displayed on the UI
    val remindersList = MutableLiveData<List<ReminderDataItem>>()

    /**
     * Get all the reminders from the DataSource and add them to the remindersList to be shown on the UI,
     * or show error if any
     */
    fun loadReminders() {
        //showloading is on of the SingleLiveEvent properties containd in BaseViewModel. It is accessible here because
        //this class extends BaseViewModel
        showLoading.value = true
        viewModelScope.launch {
            //interacting with the dataSource has to be through a coroutine
            //dataSource is a ReminderDataSource, which is an interface that provides method stubs for only the
            //four methods that retrieve, save, and delete ReminderDTOs from our Room DB
            //**I don't think this getReminders() method will work since it is ReminderDataSource which is only an
            //interface, and the full method implementation is not defined there (you'll probably need to set
            //dataSoruce as a RemindersLocalRepository instead
            val result = dataSource.getReminders()
            //Posts a task to a main thread to set the given value.
            showLoading.postValue(false)
            //dataSource.getReminders() returns a Result, which can either of type Success or Error
            when (result) {
                is Result.Success<*> -> {
                    //We define a datalist to be an Arraylist of ReminderDdataItems. These have the exact same properties as
                    //the ReminderDTOs except the ReminderDataItems are used as a data mapper between Room DB and the UI
                    val dataList = ArrayList<ReminderDataItem>()
                    //Here, we add all of the ReminderDTOs in our result list; update all of the properties of each element
                    //in this list with the property data that had been stored in the Room DB
                    dataList.addAll((result.data as List<ReminderDTO>).map { reminder ->
                        //map the reminder data from the DB to the be ready to be displayed on the UI
                        ReminderDataItem(
                            reminder.title,
                            reminder.description,
                            reminder.location,
                            reminder.latitude,
                            reminder.longitude,
                            reminder.id
                        )
                    })
                    remindersList.value = dataList
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }

            //check if no data has to be shown
            //This method is defined below separate from this method
            invalidateShowNoData()
        }
    }

    /**
     * Inform the user that there's not any data if the remindersList is empty
     */
    private fun invalidateShowNoData() {
        //showNoData is another one of the properties defined in BaseViewModel
        //it verifies whether reminderList has any data in it so that no data is displayed in that case
        showNoData.value = remindersList.value == null || remindersList.value!!.isEmpty()
    }
}