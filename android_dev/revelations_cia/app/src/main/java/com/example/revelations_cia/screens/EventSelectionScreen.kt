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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventSelectionScreen(
    onNavigateToConfirmation: (String, String) -> Unit = { _, _ -> }
) {
    var selectedGroup by remember { mutableStateOf("") }
    var numberOfEvents by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var groupError by remember { mutableStateOf("") }
    var eventsError by remember { mutableStateOf("") }

    var showSuccess by remember { mutableStateOf(false) }

    val groupOptions = listOf(
        "Technical Events",
        "Cultural Events",
        "Sports Events",
        "Literary Events",
        "Management Events"
    )

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
            text = "Event Selection",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Group Name Dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedGroup,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Group Name") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(androidx.compose.material3.MenuAnchorType.PrimaryNotEditable),
                isError = groupError.isNotEmpty(),
                supportingText = {
                    if (groupError.isNotEmpty()) {
                        Text(text = groupError, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                groupOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedGroup = option
                            expanded = false
                            groupError = ""
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Number of Events Field
        OutlinedTextField(
            value = numberOfEvents,
            onValueChange = {
                if (it.isEmpty() || (it.all { char -> char.isDigit() } && it.toIntOrNull() != null)) {
                    numberOfEvents = it
                    eventsError = ""
                }
            },
            label = { Text("Number of Events") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = eventsError.isNotEmpty(),
            supportingText = {
                if (eventsError.isNotEmpty()) {
                    Text(text = eventsError, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Submit Button
        Button(
            onClick = {
                var isValid = true

                // Validate Group Selection
                if (selectedGroup.isBlank()) {
                    groupError = "Please select a group"
                    isValid = false
                }

                // Validate Number of Events
                if (numberOfEvents.isBlank()) {
                    eventsError = "Number of events is required"
                    isValid = false
                } else {
                    val numEvents = numberOfEvents.toIntOrNull()
                    if (numEvents == null || numEvents <= 0) {
                        eventsError = "Please enter a valid number"
                        isValid = false
                    }
                }

                if (isValid) {
                    showSuccess = true
                    onNavigateToConfirmation(selectedGroup, numberOfEvents)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Continue", style = MaterialTheme.typography.titleMedium)
        }

        if (showSuccess) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Event Selection Complete!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
