# DOCUMENTACIÓ APLICACIÓ SODAPOP

## ACTIVITAT 7
L’objectiu d’aquesta activitat és la de desenvolupar el menú i la navegació de l’app, afegir llistes dinàmiques utilitzant el RecyclerView i programar els seus events i els seus filtres. També la d'incorporar el logotip animat de l’app a la pantalla inicial utilitzant Splash screen.

### Pantalla inicial (SplashScreen -> MainActivity
***
Al iniciar l'aplicació, la pantalla inicial mostra el logotip animat de l’app al centre durant uns pocs segons, i desprès apareix la pantalla principal (Main).
Per fer això, simplement hem hagut de col·locar un ImageView dins del activity_main.xml per referenciar el logo i escriure aquest petit bloc de codi: 

`findViewById<Button>(R.id.btnInvitado).setOnClickListener {
    startActivity(Intent(this, HomeActivity::class.java))
}`

### Pantalla principal (BottomNavigationView -> HomeActivity)
***
La HomeActivity conté un BottomNavigationView (menú de navegació) que permet navegar entre diferents seccions de l’app.
Per implementar-ho, hem utilitzat fragments per modularitzar les pantalles (per exemple, FragmentRebost) i controlar la transició amb el FragmentManager.

Per implementar la navegació amb el BottomNavigationView, hem utilitzat el següent codi per detectar la selecció d’un element i substituir el fragment corresponent dins del FragmentContainerView:

`val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
bottomNav.setOnItemSelectedListener { item ->
    val selectedFragment: Fragment? = when (item.itemId) {
        R.id.nav_rebost -> FragmentRebost()
        else -> null
    }
    if (selectedFragment != null) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, selectedFragment)
            .commit()
    }
    true
}`

D'aquesta manera quan l’usuari prem un element del menú, el fragment corresponent s’injecta dins del contenidor de fragments de la HomeActivity.

### Pantalla de Resultats (RecyclerView -> ResultatsActivity)
***
La pantalla ResultatsActivity mostra les receptes utilitzant un RecyclerView amb RecetaAdapter i RecetaViewHolder.
Cada element es pot seleccionar i mostra un Toast quan es clica.
A continuació mostrem un fragment del codi per configurar el RecyclerView:

`recyclerView = findViewById(R.id.recyclerView)
recyclerView.layoutManager = LinearLayoutManager(this)

adapter = RecetaAdapter(DataSource.recetas) { receta ->
    Toast.makeText(this, "Has clicat: ${receta.nombre}", Toast.LENGTH_SHORT).show()
}
recyclerView.adapter = adapter`

El RecetaAdapter s'encarrega de gestionar la llista de receptes i la inflació dels items, mentre que el RecetaViewHolder conté totes les referències a les vistes de cada item (en el nostre cas és nom i imatge) i defineix el listener per a poder fer el clic.

A continuació mostrem un fragment del codi del ViewHolder:

`class RecetaViewHolder(
    itemView: View,
    private val onItemClick: (Receta) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val imagen: ImageView = itemView.findViewById(R.id.imagenReceta)
    private val nombre: TextView = itemView.findViewById(R.id.nombreReceta)

    fun bind(receta: Receta) {
        nombre.text = receta.nombre
        imagen.setImageResource(receta.imagen)

        itemView.setOnClickListener {
            onItemClick(receta)
        }
    }
}`

També en aquesta pantalla s'ha implementat un filtrat senzill que mostra només les receptes que contenen una lletra en especific (“a” per exemple). Aquest filtre modifica la llista que es passa a l’adapter i actualitza la vista.

A continuació mostrem un fragment del codi del ResultatsActivity.kt:

`val recetasFiltradas = DataSource.recetas.filter { it.nombre.contains("s", ignoreCase = true) }

adapter = RecetaAdapter(recetasFiltradas) { receta ->
    Toast.makeText(this, "Has clicat: ${receta.nombre}", Toast.LENGTH_SHORT).show()
}
recyclerView.adapter = adapter`


Tot i que no es el filtratge definitiu per a la nostra app, aquest primer enfocament ens permetrá aplicar filtres més complexos en el futur i fer que la llista de resultats sigui més dinàmica i interactiva.
