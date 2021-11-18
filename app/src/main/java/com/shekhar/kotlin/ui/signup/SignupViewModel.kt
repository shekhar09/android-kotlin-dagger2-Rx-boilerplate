package com.shekhar.kotlin.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ccom.shekhar.kotlin.dagger.data.remote.repository.UserRepository
import com.shekhar.kotlin.ui.base.BaseViewModel
import com.shekhar.kotlin.utils.common.*
import com.shekhar.kotlin.utils.network.NetworkHelper
import com.shekhar.kotlin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SignupViewModel (
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()

    val launchDummy: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    val emailField: MutableLiveData<String> = MutableLiveData()
    val passwordField: MutableLiveData<String> = MutableLiveData()
    val nameField: MutableLiveData<String> = MutableLiveData()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)
    val nameValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.NAME)

    private fun filterValidation(field: Validation.Field) =
            Transformations.map(validationsList) {
                it.find { validation -> validation.field == field }
                        ?.run { return@run this.resource }
                        ?: Resource.unknown()
            }

    override fun onCreate() {}

    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

    fun onNameChange(name: String) = nameField.postValue(name)

    fun onSignup() {
        val email = emailField.value
        val password = passwordField.value
        val name = nameField.value

        val validations = Validator.validateSignupFields(email, password, name)
        validationsList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null && name != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successValidation.size == validations.size && checkInternetConnectionWithMessage()) {
                loggingIn.postValue(true)
                compositeDisposable.addAll(
                        userRepository.doUserSignup(email, password, name)
                                .subscribeOn(schedulerProvider.io())
                                .subscribe(
                                        {
                                            userRepository.saveCurrentUser(it)
                                            loggingIn.postValue(false)
                                            launchDummy.postValue(Event(emptyMap()))
                                        },
                                        {
                                            handleNetworkError(it)
                                            loggingIn.postValue(false)
                                        }
                                )
                )
            }
        }
    }
}