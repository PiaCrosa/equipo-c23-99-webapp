const Inventory = () => {
  return (
    <>
      <div>
        Buscar recurso
      </div>
      <div>
        <label>
          Nombre
        </label>
        <input type="text" />
      </div>
      <div>
        <label>
          Categoría
        </label>
        <select>
          <option value="">Opcion 1</option>
        </select>
      </div>
      <div>
        <button>
          Buscar
        </button>
      </div>
      <div>
        <div>
          <div>
            Proyector
          </div>
          <div>
            Descripcion
          </div>
          <div>
            <div>
              <button>
                Editar
              </button>
              <button>
                Eliminar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export {
  Inventory,
}
