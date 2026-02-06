package com.example.campusconnect.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.campusconnect.navigation.DrawerNavItem
import com.example.campusconnect.navigation.NavRoutes
@Composable
fun NavigationDrawerContent(
    currentRoute: String?,
    onItemClick: (String) -> Unit,
    onLogoutClick: () -> Unit
) {
    val drawerItems = listOf(
        DrawerNavItem(
            route = NavRoutes.Home.route,
            title = "Home",
            icon = Icons.Filled.Home
        ),
        DrawerNavItem(
            route = NavRoutes.Departments.route,
            title = "Departments",
            icon = Icons.Filled.Business
        ),
        DrawerNavItem(
            route = NavRoutes.Profile.route,
            title = "Profile",
            icon = Icons.Filled.Person
        ),
        DrawerNavItem(
            route = "logout",
            title = "Logout",
            icon = Icons.Filled.ExitToApp,
            isLogout = true
        )
    )
    ModalDrawerSheet(
        modifier = Modifier.width(280.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Campus Connect",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        drawerItems.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (item.isLogout) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (item.isLogout) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                },
                selected = isSelected && !item.isLogout,
                onClick = {
                    if (item.isLogout) {
                        onLogoutClick()
                    } else {
                        onItemClick(item.route)
                    }
                },
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}
