import './Header.scss'

interface HeaderProps {
  title: string
}

const Header: React.FC<HeaderProps> = ({ title }) => {

  return (
    <h1 className='header'>{title}</h1>
  )
}

export default Header;