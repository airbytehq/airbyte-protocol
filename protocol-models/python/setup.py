#
# Copyright (c) 2023 Airbyte, Inc., all rights reserved.
#

import pathlib

from setuptools import setup

# The directory containing this file
HERE = pathlib.Path(__file__).parent.parent

# The text of the README file
README = (HERE / "README.md").read_text()

VERSION = '0.1.0'

setup(
    name='airbyte_protocol_protocol_models',
    version=VERSION,
    description="Declares the Airbyte Protocol.",
    long_description=README,
    long_description_content_type="text/markdown",
    author="Airbyte",
    author_email="contact@airbyte.io",
    license="MIT",
    url="https://github.com/airbytehq/airbyte-protocol",
    classifiers=[
        # This information is used when browsing on PyPi.
        # Dev Status
        "Development Status :: 3 - Alpha",
        # Project Audience
        "Intended Audience :: Developers",
        "Topic :: Scientific/Engineering",
        "Topic :: Software Development :: Libraries :: Python Modules",
        "License :: OSI Approved :: MIT License",
        # Python Version Support
        "Programming Language :: Python :: 3.9",
    ],
    keywords="airbyte airbyte-protocol",
    project_urls={
        "Documentation": "https://docs.airbyte.io/",
        "Source": "https://github.com/airbytehq/airbyte-protocol",
        "Tracker": "https://github.com/airbytehq/airbyte-protocol/issues",
    },
    packages=['airbyte_protocol.protocol_models'],
    install_requires=[
        "pydantic~=1.9.2",
    ],
    python_requires=">=3.9",
)
