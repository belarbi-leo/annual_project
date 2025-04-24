export default function Background() {
    return (
      <>
        <div aria-hidden="true" className="absolute top-[-10%] left-[-10%] -z-10 transform-gpu blur-3xl opacity-50">
          <div className="h-100 w-100 bg-gradient-to-tr from-[#89c8fd] to-[#60b6ff] dark:from-[#245b90] dark:to-[#1a426a] rounded-full" style={{ filter: 'blur(100px)' }}/>
        </div>
        <div aria-hidden="true" className="absolute bottom-[5%] right-[-5%] -z-10 transform-gpu blur-3xl opacity-50">
          <div className="h-120 w-120 bg-gradient-to-tr from-[#96d629] to-[#baeb6c] dark:from-[#3f7d1c] dark:to-[#67a731] rounded-full" style={{ filter: 'blur(100px)' }}/>
        </div> 
      </>
    );
  }