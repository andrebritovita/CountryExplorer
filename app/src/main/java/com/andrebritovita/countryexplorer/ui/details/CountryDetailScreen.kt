package com.andrebritovita.countryexplorer.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andrebritovita.countryexplorer.domain.model.CountryDetail
import com.andrebritovita.countryexplorer.utils.Resource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CountryDetailScreen(
    onBackClick: () -> Unit,
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val countryName = (state as? Resource.Success)?.data?.name ?: "Details"
                    Text(countryName, maxLines = 1)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val resource = state) {
                is Resource.Loading -> CircularProgressIndicator()
                is Resource.Success -> {
                    resource.data?.let { CountryDetailContent(it) } ?: Text("Country not found.")
                }
                is Resource.Error -> Text(text = resource.message, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CountryDetailContent(country: CountryDetail) {
    val numberFormat = remember { java.text.NumberFormat.getInstance() }

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(country.flagUrl).crossfade(true).build(),
            contentDescription = "Flag of ${country.name}",
            modifier = Modifier.fillMaxWidth().height(220.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = country.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            DetailItem(label = "Capital", value = country.capital)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            DetailItem(label = "Region", value = country.region)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            DetailItem(label = "Population", value = country.population?.let { numberFormat.format(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            DetailItem(label = "Languages", value = country.languages)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            BordersSection(borders = country.borders)
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value ?: "N/A",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BordersSection(borders: List<String>?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Borders",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (borders.isNullOrEmpty()) {
            Text("This country has no land borders.", style = MaterialTheme.typography.bodyLarge)
        } else {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                borders.forEach { borderCode ->
                    SuggestionChip(
                        onClick = { /* No action for now */ },
                        label = { Text(borderCode) }
                    )
                }
            }
        }
    }
}