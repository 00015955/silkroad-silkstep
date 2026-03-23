function FilterBar({ filters, active, onChange }) {
    return (
      <div style={{ display: 'flex', gap: '0.75rem', flexWrap: 'wrap' }}>
        {filters.map((filter) => {
          const isActive = active === filter
  
          return (
            <button
              key={filter}
              className={`btn ${isActive ? 'btn-accent' : 'btn-ghost'}`}
              onClick={() => onChange(filter)}
            >
              {filter}
            </button>
          )
        })}
      </div>
    )
  }
  
  export default FilterBar 