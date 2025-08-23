package com.meetmax.designsystem.dialogs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meetmax.common.util.convertMillisToDate
import com.meetmax.common.util.currentDate
import com.meetmax.designsystem.theme.primaryBlue
import com.meetmax.common.R as CommonR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit,
    openDialog: MutableState<Boolean>,
) {
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return true
            }
        })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""//currentDate()


    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryBlue
                    ), onClick = {
                        onDateSelected(selectedDate)
                        openDialog.value = false
                    }) {
                    Text(text = stringResource(id = CommonR.string.done))
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryBlue
                    ),
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(text = stringResource(id = CommonR.string.dismiss))
                }
            }
        ) {
            DatePicker(
                title = {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = stringResource(id = CommonR.string.pick_date)
                    )
                },
                state = datePickerState,
            )
        }
    }
}

@Composable
@Preview
fun PreviewDatePickerDialog() {
    val openDialog = remember {
        mutableStateOf(true)
    }
    MyDatePickerDialog(
        onDateSelected = {},
        openDialog = openDialog
    )
}