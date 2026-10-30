package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfil(
                        nom = "Zakariya Belkassem",
                        role = "Apprenti développeur · Master ESGI",
                        description = "Étudiant en Master Ingénierie logicielle à l'ESGI Lyon, en alternance chez Ypok. " +
                            "Je crée aussi Qoul, une application de commande pour les restaurants.",
                        competences = listOf("Kotlin & Jetpack Compose", "Java / Spring Boot", "React / Vue.js", "Python / Django", "Docker & CI/CD"),
                        portfolio = "oqzak92.github.io/portfolioZak",
                        ville = "Lyon",
                        disponibilite = "En alternance chez Ypok",
                        estDisponible = true,
                        onDisponibiliteClick = {
                            // rien pour l'instant, plus tard : afficher mes dates
                        },
                        onContactClick = {
                            // rien pour l'instant, plus tard : ouvrir mon portfolio
                        },
                        // innerPadding = pour pas passer sous la barre du haut
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Ecran principal
// le padding(16.dp) donné à CarteProfil = padding EXTERNE (c'est le parent qui place la carte)
@Composable
fun EcranProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    portfolio: String,
    ville: String,
    disponibilite: String,
    estDisponible: Boolean,
    onDisponibiliteClick: () -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        CarteProfil(
            nom = nom,
            role = role,
            description = description,
            competences = competences,
            portfolio = portfolio,
            ville = ville,
            disponibilite = disponibilite,
            estDisponible = estDisponible,
            onDisponibiliteClick = onDisponibiliteClick,
            onContactClick = onContactClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

// La carte avec toutes les infos
// le padding(20.dp) dans la Surface = padding INTERNE (espace entre le bord de la carte et le contenu)
@Composable
fun CarteProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    portfolio: String,
    ville: String,
    disponibilite: String,
    estDisponible: Boolean,
    onDisponibiliteClick: () -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            EnTeteProfil(nom = nom, role = role)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )

            ListeCompetences(
                competences = competences,
                modifier = Modifier.padding(top = 16.dp)
            )

            LigneInfo(titre = "Portfolio", valeur = portfolio, modifier = Modifier.padding(top = 16.dp))
            LigneInfo(titre = "Ville", valeur = ville, modifier = Modifier.padding(top = 4.dp))

            BadgeDisponibilite(
                texte = disponibilite,
                estDisponible = estDisponible,
                onClick = onDisponibiliteClick,
                modifier = Modifier.padding(top = 16.dp)
            )

            BoutonContact(
                texte = "Contacter $nom",
                onClick = onContactClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}

// Nom + rôle
@Composable
fun EnTeteProfil(nom: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = nom,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// Titre "Compétences" + une Competence par élément de la liste
@Composable
fun ListeCompetences(competences: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Compétences",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        for (competence in competences) {
            Competence(nom = competence, modifier = Modifier.padding(top = 6.dp))
        }
    }
}

// Une compétence dans une petite pastille
@Composable
fun Competence(nom: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = nom,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp) // padding interne
        )
    }
}

// Info secondaire, ex : "Ville : Lyon"
@Composable
fun LigneInfo(titre: String, valeur: String, modifier: Modifier = Modifier) {
    Text(
        text = "$titre : $valeur",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

// Zone cliquable perso (pas un Button) donc j'ajoute l'accessibilité moi-même :
// role = Role.Button -> TalkBack dit "bouton"
// onClickLabel -> TalkBack explique l'action
// contentDescription -> description de la zone
@Composable
fun BadgeDisponibilite(
    texte: String,
    estDisponible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val couleur = if (estDisponible) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.errorContainer
    }
    val point = if (estDisponible) "● " else "○ "

    Surface(
        color = couleur,
        shape = MaterialTheme.shapes.medium,
        // chaîne de modifiers : largeur -> clic -> sémantique
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClickLabel = "voir les disponibilités", onClick = onClick)
            .semantics { contentDescription = "Statut : $texte" }
    ) {
        Text(
            text = point + texte,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp) // padding interne
        )
    }
}

// Button = déjà accessible (rôle bouton + son texte est lu par TalkBack)
@Composable
fun BoutonContact(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = texte)
    }
}

// ---------- Previews ----------

// Preview 1 : mon profil (en alternance)
@Preview(showBackground = true, name = "Zakariya - en alternance")
@Composable
fun PreviewProfilZakariya() {
    ProfilDeveloppeurTheme {
        EcranProfil(
            nom = "Zakariya Belkassem",
            role = "Apprenti développeur · Master ESGI",
            description = "Étudiant en Master Ingénierie logicielle à l'ESGI Lyon, en alternance chez Ypok. " +
                "Je crée aussi Qoul, une application de commande pour les restaurants.",
            competences = listOf("Kotlin & Jetpack Compose", "Java / Spring Boot", "React / Vue.js", "Python / Django", "Docker & CI/CD"),
            portfolio = "oqzak92.github.io/portfolioZak",
            ville = "Lyon",
            disponibilite = "En alternance chez Ypok",
            estDisponible = true,
            onDisponibiliteClick = {},
            onContactClick = {}
        )
    }
}

// Preview 2 : un autre profil, pas disponible, autres compétences
@Preview(showBackground = true, name = "Full-stack - pas disponible")
@Composable
fun PreviewProfilFullStack() {
    ProfilDeveloppeurTheme {
        EcranProfil(
            nom = "Sarah",
            role = "Développeuse full-stack",
            description = "Je fais du web et du mobile, du back-end jusqu'à l'interface.",
            competences = listOf("TypeScript", "React", "Node.js", "PostgreSQL", "Docker"),
            portfolio = "sarah-dev.fr",
            ville = "Paris",
            disponibilite = "Pas disponible avant mars",
            estDisponible = false,
            onDisponibiliteClick = {},
            onContactClick = {}
        )
    }
}
