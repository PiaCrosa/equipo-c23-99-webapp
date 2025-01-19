import React, { useState } from 'react'
import { NavbarSandwichButton } from './NavbarSandwichButton';
import { NavbarSandwichMenu } from './NavbarSandwichMenu';

const NavbarSandwich = () => {
  const [isMenuOpened, setIsMenuOpened] = useState(false);

  const toggleMenu = (value: boolean) => {
    setIsMenuOpened(value);
  }

  return (
    <React.Fragment>
      {
        !isMenuOpened
          ? <NavbarSandwichButton onToggleMenu={(value) => toggleMenu(value)} />
          : <NavbarSandwichMenu onToggleMenu={(value) => toggleMenu(value)} />
      }
    </React.Fragment>
  )
}

export {
  NavbarSandwich,
}