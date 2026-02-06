package com.example.revelations_cia.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.revelations_cia.R

@Composable
fun RegistrationScreen(
    onNavigateToEventSelection: (String, String, String, String) -> Unit = { _, _, _, _ -> }
) {
    var name by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf("") }
    var collegeName by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var mobileError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var collegeError by remember { mutableStateOf("") }

    var showSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.registration_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = ""
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError.isNotEmpty(),
            supportingText = {
                if (nameError.isNotEmpty()) {
                    Text(text = nameError, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = mobileNo,
            onValueChange = {
                if (it.length <= 10) {
                    mobileNo = it
                    mobileError = ""
                }
            },
            label = { Text("Mobile Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = mobileError.isNotEmpty(),
            supportingText = {
                if (mobileError.isNotEmpty()) {
                    Text(text = mobileError, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = emailId,
            onValueChange = {
                emailId = it
                emailError = ""
            },
            label = { Text("Email ID") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError.isNotEmpty(),
            supportingText = {
                if (emailError.isNotEmpty()) {
                    Text(text = emailError, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = collegeName,
            onValueChange = {
                collegeName = it
                collegeError = ""
            },
            label = { Text("College Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = collegeError.isNotEmpty(),
            supportingText = {
                if (collegeError.isNotEmpty()) {
                    Text(text = collegeError, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                var isValid = true

                if (name.isBlank()) {
                    nameError = "Name is required"
                    isValid = false
                }

                if (mobileNo.isBlank()) {
                    mobileError = "Mobile number is required"
                    isValid = false
                } else if (mobileNo.length != 10) {
                    mobileError = "Mobile number must be 10 digits"
                    isValid = false
                } else if (!mobileNo.all { it.isDigit() }) {
                    mobileError = "Mobile number must contain only digits"
                    isValid = false
                }

                if (emailId.isBlank()) {
                    emailError = "Email is required"
                    isValid = false
                } else if (!emailId.contains("@") || !emailId.contains(".")) {
                    emailError = "Invalid email format"
                    isValid = false
                }

                if (collegeName.isBlank()) {
                    collegeError = "College name is required"
                    isValid = false
                }

                if (isValid) {
                    showSuccess = true
                    onNavigateToEventSelection(name, mobileNo, emailId, collegeName)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Submit", style = MaterialTheme.typography.titleMedium)
        }

        if (showSuccess) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Registration Successful!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
